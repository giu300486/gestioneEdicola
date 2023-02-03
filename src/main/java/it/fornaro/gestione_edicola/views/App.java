package it.fornaro.gestione_edicola.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import it.fornaro.gestione_edicola.components.MenuEdicola;
import it.fornaro.gestione_edicola.components.TabsEdicola;

@Route(value = "", layout = MainLayout.class)
public class App extends Composite<Component> {

    private MenuEdicola menuEdicola;
    private TabsEdicola tabsEdicola;

    @Override
    protected Component initContent() {
        this.menuEdicola = new MenuEdicola();
        this.tabsEdicola = new TabsEdicola();

        return new VerticalLayout(menuEdicola,tabsEdicola);
    }
}
