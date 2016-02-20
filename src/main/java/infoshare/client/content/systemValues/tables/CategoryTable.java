package infoshare.client.content.systemValues.tables;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;
import infoshare.client.content.MainLayout;
import infoshare.domain.Category;
import infoshare.services.category.CategoryService;
import infoshare.services.category.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by codex on 2015/06/26.
 */
public class CategoryTable extends Table {

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    private MainLayout mainLayout;

    public CategoryTable(MainLayout mainApp){
        this.mainLayout = mainApp;
        setSizeFull();
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.TABLE_BORDERLESS);
        addStyleName(ValoTheme.TABLE_NO_STRIPES);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        addContainerProperty("Tip Category", String.class, null);
        addContainerProperty("Description", String.class,null);

        List<Category> categories = categoryService.findAll();

        for (Category category: categories){
            addItem(new Object[]{

                    category.getName(),
                            category.getDescription()
            },category.getId());
        }

        setSelectable(true);
        setImmediate(true);
        setNullSelectionAllowed(false);
    }

}