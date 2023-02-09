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
import it.fornaro.gestione_edicola.model.Periodo;
import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.views.AnagraficaRiviste;

public class TabsEdicola extends Composite<Component> {

    private VerticalLayout tabsContainer;

    private Tab archivio;
    private Tab carico;
    private Tab resi;
    private Tab scarico;

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

        setContent(tabs.getSelectedTab());

        Div div = new Div();
        div.setWidthFull();
        div.add(tabs,tabsContainer);

        tabs.addSelectedChangeListener(new ComponentEventListener<Tabs.SelectedChangeEvent>() {
            @Override
            public void onComponentEvent(Tabs.SelectedChangeEvent event) {
                Tab selected = event.getSelectedTab();
                setContent(selected);
            }
        });

        return div;
    }

    private void setContent(Tab selected) {
        tabsContainer.removeAll();

        if(archivio.equals(selected)) {
            tabsContainer.add(buildArchivioTab());
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

    private Component buildArchivioTab() {
        AnagraficaRiviste anagraficaRiviste = new AnagraficaRiviste(new Rivista());
        return anagraficaRiviste.getContent();
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
