package production;

import item.*;

public class Container {
    private boolean empty = true;
    private boolean clean = true;
    private String content = "";
    private int quantity = 0;

    public void useContainer(Item drink) {
        this.content = drink.getName();
        this.empty = false;
        this.quantity = drink.getCount();
    }

    public void emptyContainer() {
        this.content = "";
        this.quantity = 0;
        this.empty = false;
        this.clean = false;
    }

    public void cleanContainer() {
        this.clean = true;
    }

    public String getContent() {
        return this.content;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
