import spock.lang.Specification
import spock.lang.Unroll

import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ValidatorSpec extends Specification {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName('nashorn');

    private loadJS(String path) {
        def source = this.class.getResource(path).text
        engine.eval(source);
    }

    @Unroll
    def "#person is valid? #expectedResult"() {
        setup:
        loadJS('/js/Validators.js')

        when:
        boolean result = engine.invokeFunction('isValid', person)

        then:
        result == expectedResult

        where:
        expectedResult || person
        false          || null
        false          || [:]
        false          || [name: [first: 'James']]
        false          || [name: [last: 'Bond']]
        true           || [name: [first: 'James', last: 'Bond']]
    }

}
