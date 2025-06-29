package tqs;


import tqs.euromillions.CuponEuromillions;
import tqs.euromillions.Dip;
import tqs.euromillions.EuromillionsDraw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * demonstrates a client for random euromillions bets
 */
public class DemoMain {
    static final Logger log = LoggerFactory.getLogger(lookup().lookupClass());

    public static void main(String[] args) {

        // simulate a coupon with three user bets
        CuponEuromillions myBet = new CuponEuromillions();
        myBet.appendDip(Dip.generateRandomDip());
        myBet.appendDip(Dip.generateRandomDip());
        myBet.appendDip(Dip.generateRandomDip());
        String formattedBet = myBet.format();
        if (formattedBet != null)
            log.info("Betting with three random bets \n{} ", formattedBet);



        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();
        String formattedDrawResults = draw.getDrawResults().format();
        if (formattedDrawResults != null)
            log.info("Draw results:\n{}", formattedDrawResults );

        //report results
        log.info("You scored (matches): ");
        for (Dip dip : draw.findMatchesFor(myBet)) {
            String formattedDip = dip.format();
            if (formattedDip != null)
                log.info( formattedDip);
        }
    }
}
