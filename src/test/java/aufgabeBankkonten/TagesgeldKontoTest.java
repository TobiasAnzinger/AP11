package aufgabeBankkonten;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TagesgeldKontoTest {

    @Mock
    Kunde kundeMock;

    GiroKonto giroKonto;

    double amount1 = 120.34;
    double amount2 = -120.34;
    double amount3 = 0;

    @Before
    public void setUp(){
        giroKonto = new GiroKonto(kundeMock, 1);
        giroKonto.setSaldo(1000);
    }


    @Test
    public void einzahlenTest() {
        assertTrue(giroKonto.einzahlen(amount1));
        assertFalse(giroKonto.einzahlen(amount2));
        assertFalse(giroKonto.einzahlen(amount3));
        assertEquals(1000 + amount1, giroKonto.getSaldo());
    }

    @Test
    public void abhebenTest() {
        assertTrue(giroKonto.abheben(amount1));
        assertEquals(1000 - amount1, giroKonto.getSaldo());
        assertFalse(giroKonto.abheben(amount2));
        assertEquals(1000 - amount1, giroKonto.getSaldo());
        assertFalse(giroKonto.abheben(amount3));
        assertEquals(1000 - amount1 - amount3, giroKonto.getSaldo());
    }

}