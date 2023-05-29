Book gina = new Book('Groovy in Action')
assert gina.title == 'Groovy in Action'
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

String getTitleBackwards(book) {
    title = book.getTitle()
    return title.reverse()
}