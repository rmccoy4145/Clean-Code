package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles)
    {
        if (roles == null) return;
        
        
        menuItemsList.forEach((menuItem) ->
        {
               authorizeMenuItem(roles, menuItem);
        });

    }

    private void authorizeMenuItem(Role[] roles, MenuItem menuItem)
    {
        final String readAccessRole = menuItem.getReadAccessRole();
        final String writeAccessRole = menuItem.getWriteAccessRole();
        Arrays.asList(roles).stream().forEach((role) ->
        {
            final String roleName = role.getName();
            if (roleName.equals(readAccessRole))
            {
                authorizeMenuItemAccess(menuItem, Constants.READ);
  
            }
            
            if (roleName.equals(writeAccessRole))
            {
                authorizeMenuItemAccess(menuItem, Constants.WRITE);
            }
        });
    }

    private void authorizeMenuItemAccess(MenuItem menuItem, String accessType)
    {
                        menuItem.setAccess(accessType);
                        menuItem.setVisible(true);
    }

}
