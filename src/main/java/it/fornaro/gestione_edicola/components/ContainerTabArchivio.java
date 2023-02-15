package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import it.fornaro.gestione_edicola.service.RivistaService;

public class ContainerTabArchivio extends Composite<Component> {

    protected VerticalLayout container;
    protected TabsEdicola tabsEdicola;

    protected final RivistaService rivistaService;

    public ContainerTabArchivio(TabsEdicola tabsEdicola, RivistaService rivistaService) {
        this.rivistaService = rivistaService;
        this.tabsEdicola = tabsEdicola;
        this.container = new VerticalLayout();
    }

    public Component buildContent() {
        return this.container;
    }
}
