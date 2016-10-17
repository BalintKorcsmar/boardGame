package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    BoardTest.class,
    TicTacToeTest.class,
    PerfectPlayerTest.class,
    MyConsultantTest.class,
    ConsultantTest.class
})

public class ConsultantTestSuite {

}