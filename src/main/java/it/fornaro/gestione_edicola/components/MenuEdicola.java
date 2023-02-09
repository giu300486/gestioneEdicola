package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import it.fornaro.gestione_edicola.service.RivistaService;

public class MenuEdicola extends Composite<Component> {

    private final RivistaService rivistaService;

    public MenuEdicola(RivistaService rivistaService) {
        this.rivistaService = rivistaService;
    }

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
