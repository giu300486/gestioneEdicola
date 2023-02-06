package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.StreamResource;

public class MenuBarTooltip extends Composite<Component> {

    @Override
    protected Component initContent() {
        MenuBar menuBar = new MenuBar();

        StreamResource nuovaRivistaStreamResource = new StreamResource("nuovaRivista",
                () -> getClass().getResourceAsStream("/static/nuovaRivista.gif"));
        StreamResource modificaRivistaStreamResource = new StreamResource("modificaRivista",
                () -> getClass().getResourceAsStream("/static/modificaRivista.gif"));
        StreamResource eliminaRivistaStreamResource = new StreamResource("eliminaRivista",
                () -> getClass().getResourceAsStream("/static/eliminaRivista.gif"));
        StreamResource archivioRivisteStreamResource = new StreamResource("archivioRiviste",
                () -> getClass().getResourceAsStream("/static/archivioRiviste.gif"));

        createIconItem(menuBar, new Image(nuovaRivistaStreamResource, ""), "Nuova Rivista");
        createIconItem(menuBar, new Image(modificaRivistaStreamResource, ""), "Modifica Rivista");
        createIconItem(menuBar, new Image(eliminaRivistaStreamResource, ""), "Elimina Rivista");
        createIconItem(menuBar, new Image(archivioRivisteStreamResource, ""), "Archivio");

        return menuBar;
    }

    private MenuItem createIconItem(MenuBar menu, Image image,
                                    String tooltipText) {
        MenuItem item = menu.addItem(image, tooltipText);
        return item;
    }
}
