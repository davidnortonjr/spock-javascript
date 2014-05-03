function isValid(person) {
    return person != null
        && person.name != null
        && person.name.first != null
        && person.name.last != null;
}