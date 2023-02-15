package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.StreamResource;
import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.service.RivistaService;

public class TabsEdicola extends Composite<Component> {

    private VerticalLayout tabsContainer;

    private Tab archivio;
    private Tab carico;
    private Tab resi;
    private Tab scarico;

    private final RivistaService rivistaService;

    public TabsEdicola(RivistaService rivistaService) {
        this.rivistaService = rivistaService;
    }

    @Override
    protected Component initContent() {
        tabsContainer = new VerticalLayout();

        StreamResource archivioStreamResource = new StreamResource("archivio",
                () -> getClass().getResourceAsStream("/static/archivio.gif"));
        StreamResource caricoStreamResource = new StreamResource("carico",
                () -> getClass().getResourceAsStream("/static/carico.gif"));
        StreamResource resiStreamResource = new StreamResource("resi",
                () -> getClass().getResourceAsStream("/static/resiScarico.gif"));
        StreamResource scaricoStreamResource = new StreamResource("scarico",
                () -> getClass().getResourceAsStream("/static/resiScarico.gif"));

        archivio = new Tab(new Image(archivioStreamResource, ""), new Span("Archivio"));
        carico = new Tab(new Image(caricoStreamResource, ""), new Span("Carico"));
        resi = new Tab(new Image(resiStreamResource, ""), new Span("Resi"));
        scarico = new Tab(new Image(scaricoStreamResource, ""), new Span("Scarico"));

        Tabs tabs = new Tabs(archivio,carico,resi,scarico);
        tabs.setWidthFull();
        tabs.setFlexGrowForEnclosedTabs(1);

        setContent(tabs.getSelectedTab(),1);

        Div div = new Div();
        div.setWidthFull();
        div.add(tabs,tabsContainer);

        tabs.addSelectedChangeListener(new ComponentEventListener<Tabs.SelectedChangeEvent>() {
            @Override
            public void onComponentEvent(Tabs.SelectedChangeEvent event) {
                Tab selected = event.getSelectedTab();
                setContent(selected,1);
            }
        });

        return div;
    }

    public Tab getArchivio() {
        return archivio;
    }

    public Tab getCarico() {
        return carico;
    }

    public Tab getResi() {
        return resi;
    }

    public Tab getScarico() {
        return scarico;
    }

    public void setContent(Tab selected, int content) {
        tabsContainer.removeAll();

        if(archivio.equals(selected)) {
            tabsContainer.add(buildArchivioTab(content));
        }
        else if(carico.equals(selected)) {
            tabsContainer.add(buildCaricoTab());
        }
        else if(resi.equals(selected)) {
            tabsContainer.add(buildResiTab());
        }
        else if(scarico.equals(selected)) {
            tabsContainer.add(buildScaricoTab());
        }
    }

    private Component buildArchivioTab(int content) {
        switch (content) {
            case 1: {
                ContainerTabArchivio anagraficaRiviste = new AnagraficaRiviste(new Rivista(), this.rivistaService,this);
                anagraficaRiviste.getContent();
                return anagraficaRiviste.buildContent();
            }
            case 2: {
                ContainerTabArchivio tabellaRivista = new TabellaRivista(this.rivistaService, this);
                tabellaRivista.getContent();
                return tabellaRivista.buildContent();
            }
            default: {
                return null;
            }
        }
    }

    private Component buildCaricoTab() {
        return new H1("CARICO");
    }

    private Component buildResiTab() {
        return new H1("RESI");
    }

    private Component buildScaricoTab() {
        return new H1("SCARICO");
    }
}
