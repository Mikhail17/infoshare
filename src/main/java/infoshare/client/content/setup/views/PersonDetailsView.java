package infoshare.client.content.setup.views;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import infoshare.client.content.MainLayout;
<<<<<<< HEAD:src/main/java/infoshare/client/content/setup/views/PersonDetailsView.java
import infoshare.client.content.setup.tables.PersonDetailsTable;
=======
import infoshare.client.content.setup.SetupMenu;
import infoshare.client.content.setup.forms.UserDetailsForm;
import infoshare.client.content.setup.models.UserModel;
import infoshare.client.content.setup.tables.UserDetailsTable;
import infoshare.domain.User;
>>>>>>> fa6ff7d925bc1f5383382a2409f93a590eb6edf5:src/main/java/infoshare/client/content/setup/views/UserDetailsView.java
import infoshare.filterSearch.UserFilter;
import infoshare.services.users.Impl.UserServiceImpl;
import infoshare.services.users.UserService;

/**
 * Created by user9 on 2016/02/11.
 */
public class PersonDetailsView extends VerticalLayout implements
        Button.ClickListener, Property.ValueChangeListener {

    private final Window popUp;
    private final MainLayout main;
<<<<<<< HEAD:src/main/java/infoshare/client/content/setup/views/PersonDetailsView.java
    private final PersonDetailsTable table;
=======
    private final UserDetailsForm form;
    private final UserDetailsTable table;
>>>>>>> fa6ff7d925bc1f5383382a2409f93a590eb6edf5:src/main/java/infoshare/client/content/setup/views/UserDetailsView.java
    private final UserFilter userDetails;
    private UserService userService = new UserServiceImpl();

    public PersonDetailsView(MainLayout app) {
        main = app;
<<<<<<< HEAD:src/main/java/infoshare/client/content/setup/views/PersonDetailsView.java
        table = new PersonDetailsTable(main);
=======
        this.form = new UserDetailsForm();
        table = new UserDetailsTable(main);
>>>>>>> fa6ff7d925bc1f5383382a2409f93a590eb6edf5:src/main/java/infoshare/client/content/setup/views/UserDetailsView.java
        userDetails = new UserFilter();
        this.popUp = modelWindow();
        HorizontalLayout layout = new HorizontalLayout();

        layout.setSpacing(false);
        layout.addComponent(userDetails.field);
        setSpacing(false);
        addListeners();
        addComponent(layout);
        addComponent(table);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        final Button source = event.getButton();

        if (source == form.popUpBackBtn){
            popUp.setModal(false);
            UI.getCurrent().removeWindow(popUp);
            getHome();
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {

    }
    public void refreshContacts(String stringFilter ) {
        try {
            table.removeAllItems();
            userDetails.findAll(stringFilter,"CPUT").forEach(this.table::loadTable);
        }catch (Exception e){
        }
    }
    public void addListeners(){

        form.popUpBackBtn.addClickListener((Button.ClickListener) this);
        table.addValueChangeListener(this);
        userDetails.field.addTextChangeListener((FieldEvents.TextChangeListener)
                textChangeEvent -> refreshContacts(textChangeEvent.getText()));

        table.addItemClickListener(clickEvent -> {
            int i = 1;
            if (clickEvent.isDoubleClick())
            {
                if (i==1) {
                    i = 0;
                    try {
                        User user = userService.find(table.getValue().toString());
                        final UserModel bean = getModel(user);
                        form.binder.setItemDataSource(new BeanItem<>(bean));
                        UI.getCurrent().addWindow(popUp);
                        popUp.setModal(true);
                        //getHome();
                        }catch (Exception e){
                        Notification.show("Select the row you wanna edit",
                                Notification.Type.HUMANIZED_MESSAGE);
                    }
                }
            }
        });
    }
    private UserModel getModel(User val) {

        final UserModel model = new UserModel();
        final User user = userService.find(val.getId());
        model.setAddress(user.getAddress());
        model.setContact(user.getContact());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setEnable(user.isEnable());
        model.setOtherName(user.getOtherName());
        model.setRole(user.getRole());
        model.setUsername(user.getUsername());
        model.setPassword(user.getPassword());
        return model;
    }

    private Window modelWindow(){
        final Window popup = new Window();
        popup.setWidth(60.0f, Unit.PERCENTAGE);
        popup.setHeight(55.0f, Unit.PERCENTAGE);
        popup.setClosable(false);
        popup.setResizable(false);
        popup.setContent(form);
        return popup;
    }

    private void getHome() {
        main.content.setSecondComponent(new SetupMenu(main, "DETAILS"));
    }

}
