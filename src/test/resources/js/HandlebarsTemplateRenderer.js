function render(template, model) {
    var template = Handlebars.compile(template);
    return template(model);
}
