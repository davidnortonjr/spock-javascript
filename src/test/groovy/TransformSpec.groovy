import spock.lang.Specification
import spock.lang.Unroll

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class TransformSpec extends Specification {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName('nashorn');

    def setup() {
        def source = this.class.getResource('/js/transforms.js').text
        engine.eval(source);
    }

    def "transform"() {
        when:
        def result = engine.invokeFunction('transform')

        then:
        result.firstName == 'James'
        result.lastName == 'Bond'
    }

}
