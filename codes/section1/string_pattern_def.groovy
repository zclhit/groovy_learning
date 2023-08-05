twister = 'she sells sea shells at the sea shore of seychelles'

regex = /\b(\w)\w*\1\b/

start = System.currentTimeMillis()
100000.times {
    twister =~ regex
//    识别10w次
}
first = System.currentTimeMillis() - start

start = System.currentTimeMillis()
pattern = ~regex
100000.times {
    pattern.matcher(twister)
}
second = System.currentTimeMillis() - start

assert first > second * 1.20