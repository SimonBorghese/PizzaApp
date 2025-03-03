import libpizza.*;

import java.util.List;

public class Driver {
    public static void main(String[] args){
        Dominos dm = new Dominos();
        PapaJohns pj = new PapaJohns();

        String dominos_locations = dm.getLocationJson("12345");
        String dominos_menu = dm.getMenuJson("4267");

        String papajohns_locations = pj.getLocationJson("12345");
        String papajohns_menu = pj.getMenuJson("5390");

        // A breakpoint should be used to read the output
    }
}
