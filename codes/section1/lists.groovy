//罗马数字列表
def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
//访问列表
assert roman[4] == 'IV'
//扩张列表
roman[8] = 'VIII'
assert roman.size() == 9