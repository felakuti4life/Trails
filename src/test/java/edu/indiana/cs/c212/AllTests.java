package edu.indiana.cs.c212;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.indiana.cs.c212.gameMechanics.LoseByConnectingRulesTest;
import edu.indiana.cs.c212.gameMechanics.MoveTest;
import edu.indiana.cs.c212.gameMechanics.StandardRulesTest;


@RunWith(Suite.class)
@SuiteClasses({	MoveTest.class, 
				StandardRulesTest.class,
				LoseByConnectingRulesTest.class,
				})
public class AllTests {

}
