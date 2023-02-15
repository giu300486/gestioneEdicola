package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.StreamResource;

public class MenuBarTooltip extends Composite<Component> {

    private static final String NUOVA_RIVISTA = "Nuova Rivista";
    private static final String MODIFICA_RIVISTA = "Modifica Rivista";
    private static final String ELIMINA_RIVISTA = "Elimina Rivista";
    private static final String ARCHIVIO = "Archivio";

    private TabsEdicola tabsEdicola;

    public MenuBarTooltip(TabsEdicola tabsEdicola) {
        this.tabsEdicola = tabsEdicola;
    }

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

        createMenuItem(menuBar, new Image(nuovaRivistaStreamResource, ""), NUOVA_RIVISTA);
        createMenuItem(menuBar, new Image(modificaRivistaStreamResource, ""), MODIFICA_RIVISTA);
        createMenuItem(menuBar, new Image(eliminaRivistaStreamResource, ""), ELIMINA_RIVISTA);
        createMenuItem(menuBar, new Image(archivioRivisteStreamResource, ""), ARCHIVIO);

        return menuBar;
    }

    private MenuItem createMenuItem(MenuBar menu, Image image,
                                    String tooltipText) {
        MenuItem item = menu.addItem(image, tooltipText);

        switch (tooltipText) {
            case NUOVA_RIVISTA : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {

                        MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(),1);
                    }
                });
                break;
            }
            case MODIFICA_RIVISTA : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {

                    }
                });
                break;
            }
            case ELIMINA_RIVISTA : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {

                    }
                });
                break;
            }
            case ARCHIVIO : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {

                        MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(),2);
                    }
                });
                break;
            }
        }

        return item;
    }
}
