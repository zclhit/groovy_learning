class BookBean {
    String title //属性
}

def groovyBook = new BookBean()

//通过显示的方法调用来使用属性
groovyBook.setTitle('Groovy conquers the world')
assert groovyBook.getTitle() == 'Groovy conquers the world'

//通过groovy的快捷方式来使用属性
groovyBook.title = 'Groovy in Action'
assert groovyBook.title == 'Groovy in Action'