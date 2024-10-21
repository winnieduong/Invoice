import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<LineItem> lineItems = new ArrayList<>();

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double getTotalAmountDue() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
