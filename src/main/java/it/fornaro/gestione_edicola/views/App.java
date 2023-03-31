package it.fornaro.gestione_edicola.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import it.fornaro.gestione_edicola.components.TabsEdicola;
import it.fornaro.gestione_edicola.components.MenuEdicola;
import it.fornaro.gestione_edicola.service.RivistaService;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "edicola", layout = MainLayout.class)
public class App extends Composite<Component> {

    private MenuEdicola menuEdicola;
    private TabsEdicola tabsEdicola;

    private final RivistaService rivistaService;

    @Autowired
    public App(RivistaService rivistaService) {
        this.rivistaService = rivistaService;
    }

    @Override
    protected Component initContent() {
        this.menuEdicola = new MenuEdicola(this.rivistaService);
        this.tabsEdicola = new TabsEdicola(this.rivistaService);

        return new VerticalLayout(menuEdicola,tabsEdicola);
    }
}
