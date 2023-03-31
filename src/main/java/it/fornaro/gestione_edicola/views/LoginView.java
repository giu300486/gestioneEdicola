package it.fornaro.gestione_edicola.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import it.fornaro.gestione_edicola.components.LoginComponent;
import it.fornaro.gestione_edicola.config.Config;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainLayout.class)
public class LoginView extends Composite<Component> {

    private static final String LOGIN = "/login";

    private Config config;

    private HorizontalLayout container;
    private LoginComponent loginComponent;

    @Autowired
    public LoginView(Config config) {
        this.config = config;

        this.loginComponent = new LoginComponent(this.config.getCliendId(),this.config.getDataLoginUri());
    }

    @Override
    protected Component initContent() {
        this.container = new HorizontalLayout(this.loginComponent);
        this.container.setHeight(100,Unit.PERCENTAGE);
        this.container.setAlignSelf(FlexComponent.Alignment.CENTER,this.loginComponent);
        this.container.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return this.container;
    }
}
