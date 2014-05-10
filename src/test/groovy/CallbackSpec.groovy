import spock.lang.Specification

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import java.util.function.Function

class CallbackSpec extends Specification {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName('nashorn')

    def "callback stub called"() {
        setup:
        engine.eval('''function thing(callback) { return callback('some') + 'thing'; }''')

        when:
        def result = engine.invokeFunction('thing', { return 'SOME' } as Function)

        then:
        result == 'SOMEthing'
    }

    def "callback called"() {
        setup:
        engine.eval('''function thing(callback) { return callback('some') + 'thing'; }''')
        def callback = Mock(Function)

        when:
        def result = engine.invokeFunction('thing', callback)

        then:
        1 * callback.apply('some') >> 'SOME'
        result == 'SOMEthing'
    }

    def "alert called"() {
        setup:
        engine.eval('''function doAlert() { alert('something'); }''')
        def alert = Mock(Function)
        engine.put('alert', alert)

        when:
        engine.invokeFunction('doAlert')

        then:
        1 * alert.apply('something')
    }


}
