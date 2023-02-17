package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.server.StreamResource;
import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.views.dialogs.DialogGestioneEdicola;

import java.util.Objects;

public class MenuBarTooltip extends Composite<Component> {

    private static final String NUOVA_RIVISTA = "Nuova Rivista";
    private static final String MODIFICA_RIVISTA = "Modifica Rivista";
    private static final String ELIMINA_RIVISTA = "Elimina Rivista";
    private static final String ARCHIVIO = "Archivio";

    private TabsEdicola tabsEdicola;
    private Rivista rivista;

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
                        TabsEdicola.STATE = 1;
                        MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(), new Rivista(), TabsEdicola.STATE);
                    }
                });
                break;
            }
            case MODIFICA_RIVISTA : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {
                        if(TabsEdicola.STATE == 2) {
                            if (Objects.isNull(rivista)) {
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Selezionare una riga della tabella");
                                dialogGestioneEdicola.open();
                            } else {
                                TabsEdicola.STATE = 1;
                                MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(), rivista, TabsEdicola.STATE);
                            }
                        }
                        else if(TabsEdicola.STATE == 1) {
                            rivista = tabsEdicola.getRivistaService().findAll().get(0);
                            MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(), rivista, TabsEdicola.STATE);
                        }
                    }
                });
                break;
            }
            case ELIMINA_RIVISTA : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {
                        if(TabsEdicola.STATE == 2) {
                            if (Objects.isNull(rivista)) {
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Selezionare una riga della tabella");
                                dialogGestioneEdicola.open();
                            } else {
                                String descrizione = rivista.getDescrizione();
                                tabsEdicola.getRivistaService().delete(rivista);
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Rivista " + descrizione + " eliminata");
                                dialogGestioneEdicola.open();
                                MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(), rivista, TabsEdicola.STATE);
                            }
                        }
                        else if(TabsEdicola.STATE == 1) {
                            tabsEdicola.getAnagraficaRiviste().getBinderBarcode().validate();
                            if(!tabsEdicola.getAnagraficaRiviste().getBinderBarcode().isValid()) {
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Per favore sistemare gli errori");
                                dialogGestioneEdicola.open();
                            }
                            else {
                                String barcode = tabsEdicola.getAnagraficaRiviste().getTextFieldBarCode().getValue();
                                Rivista rivistaFound = tabsEdicola.getRivistaService().findByBarcode(barcode);
                                if(!Objects.isNull(rivistaFound)) {
                                    tabsEdicola.getRivistaService().delete(rivistaFound);
                                    DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Rivista " + rivistaFound.getDescrizione() + " eliminata");
                                    dialogGestioneEdicola.open();
                                }
                                else {
                                    DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Errore nel processo di eliminazione della rivista! La rivista non esiste");
                                    dialogGestioneEdicola.open();
                                }
                            }
                        }
                    }
                });
                break;
            }
            case ARCHIVIO : {
                item.addClickListener(new ComponentEventListener<ClickEvent<MenuItem>>() {
                    @Override
                    public void onComponentEvent(ClickEvent<MenuItem> event) {
                        TabsEdicola.STATE = 2;
                        MenuBarTooltip.this.tabsEdicola.setContent(tabsEdicola.getArchivio(), null, TabsEdicola.STATE);
                    }
                });
                break;
            }
        }

        return item;
    }

    public void setRivista(Rivista rivista) {
        this.rivista = rivista;
    }
}
