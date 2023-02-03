package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;

public class MenuEdicola extends Composite<Component> {

    @Override
    protected Component initContent() {
        MenuBar menuBar = new MenuBar();
        MenuItem menu = menuBar.addItem("Menu");
        menu.getSubMenu().addItem("Lista Riviste e Giacenze");
        menu.getSubMenu().addItem("Lista Carico");
        menu.getSubMenu().addItem("Lista Resi");
        menu.getSubMenu().addItem("Lista Scarico");

        return menuBar;
    }
}
