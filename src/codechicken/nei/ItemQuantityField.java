package codechicken.nei;

public class ItemQuantityField extends TextField {
    public ItemQuantityField(String ident) {
        super(ident);
        centered = true;
    }

    // Find a way to work this this back in
    public boolean isValid(String string) {
        if (string.equals(""))
            return true;

        try {
            return Integer.parseInt(string) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int intValue() {
        return intValue(text());
    }

    public int intValue(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    @Override
    public void loseFocus() {
        setText(Integer.toString(intValue()));
    }

    boolean req = false;

    @Override
    public void onTextChange(String oldText) {
//        if (intValue(oldText) != intValue())//hacky recursion stopper // hackly solution does not work, stupid strait solution works
        if (req) {
            return;
        }
        req = true;
        NEIClientUtils.setItemQuantity(intValue());
        req = false;
    }
}
