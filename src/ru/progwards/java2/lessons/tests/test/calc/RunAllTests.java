// Oleg Kiselev
// 26.05.2020, 15:37

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DivSimpleCalculatorTest.class, MultSimpleCalculatorTest.class,
        DiffSimpleCalculatorTest.class, SumSimpleCalculatorTest.class})
public class RunAllTests {

}
