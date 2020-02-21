import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class DustTemplateSpec extends Specification {
    @Shared
    ScriptEngine engine = new ScriptEngineManager().getEngineByName('nashorn')

    private loadJS(String path) {
        def source = this.class.getResource(path).text
        engine.eval(source)
    }

    void setupSpec() {
        loadJS('/js/lib/dust-full.js')
        loadJS('/js/DustTemplateRenderer.js')
    }

    @Unroll
    def 'dust template'() {
        setup:
        def template = this.class.getResource('dust/greeting.dust').text

        expect:
        engine.invokeFunction('render', template, model) == expectedOutput

        where:
        model                                  || expectedOutput
        [name: [first: 'James', last: 'Bond']] || 'The name is Bond. James Bond.'
        [name: [last: 'Bond']]                 || 'The name is Bond.  Bond.'
    }
}
