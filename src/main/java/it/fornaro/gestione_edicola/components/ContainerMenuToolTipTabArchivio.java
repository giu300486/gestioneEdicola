package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import it.fornaro.gestione_edicola.model.Rivista;

public class ContainerMenuToolTipTabArchivio extends Composite<Component> {

    private MenuBarTooltip menuBarTooltip;
    private VerticalLayout container;
    private HorizontalLayout northContainer;
    private TabsEdicola tabsEdicola;

    public ContainerMenuToolTipTabArchivio(TabsEdicola tabsEdicola) {
        this.tabsEdicola = tabsEdicola;
    }

    @Override
    protected Component initContent() {
        this.container = new VerticalLayout();

        this.northContainer = new HorizontalLayout();

        this.menuBarTooltip = new MenuBarTooltip(tabsEdicola);

        this.northContainer.add(this.menuBarTooltip);
        this.northContainer.setMinWidth(50,Unit.PERCENTAGE);

        this.container.add(this.northContainer);

        return this.container;
    }

    public void setRivistaToMenuBarToolTip(Rivista rivista) {
        this.menuBarTooltip.setRivista(rivista);
    }
}
