package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.shared.ui.LoadMode;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("div")
@JavaScript(value = "https://accounts.google.com/gsi/client",loadMode = LoadMode.LAZY)
public class LoginComponent extends Component {

    private Element div;
    private Element div1;

    public LoginComponent(String clientId, String loginUri) {

        this.div = new Element("div");
        this.div.setAttribute("id", "g_id_onload");
        this.div.setAttribute("data-client_id", clientId);
        this.div.setAttribute("data-login_uri", loginUri);
        this.div.setAttribute("data-auto_prompt", "false");

        this.div1 = new Element("div");
        this.div1.setAttribute("class", "g_id_signin");
        this.div1.setAttribute("data-type", "standard");
        this.div1.setAttribute("data-size", "large");
        this.div1.setAttribute("data-theme", "outline");
        this.div1.setAttribute("data-text", "sign_in_with");
        this.div1.setAttribute("data-shape", "rectangular");
        this.div1.setAttribute("data-logo_alignment", "left");

        getElement().appendChild(this.div,this.div1);
    }
}
