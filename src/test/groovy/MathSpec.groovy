import spock.lang.Specification
import spock.lang.Unroll

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MathSpec extends Specification {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName('nashorn')

    @Unroll
    def 'Max of #a and #b is #expectedResult'() {
        when:
        def math = engine.eval('Math')
        def result = engine.invokeMethod(math, 'max', a, b)

        then:
        result == expectedResult

        where:
        a    | b | expectedResult
        0    | 1 | 1
        2    | 1 | 2
        -2   | 1 | 1
        null | 1 | 1
    }
}
