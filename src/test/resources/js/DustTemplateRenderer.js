function render(template, model) {
    var compiled = dust.compile(template, 'mytemplate');
    dust.loadSource(compiled);
    var result;
    dust.render('mytemplate', model, function(err, out) {
        result = out;
    });
    return result;
}
