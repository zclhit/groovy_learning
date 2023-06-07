me = 'Tarzan'
you = 'Jane'
line = "me $me - you $you"
assert line == 'me Tarzan - you Jane'

date = new Date(0)
out = "Year $date.year Month $date.month Day $date.date"
assert  out == 'Year 70 Month 0 Day 1'

out = "Date is ${date.toGMTString()} !"
assert out == 'Date is 1 Jan 1970 00:00:00 GMT !'

sql = """
SELECT FROM MyTable
WHERE Year = $date.year
"""
assert sql == """
SELECT FROM MyTable
WHERE Year = 70
"""

out = "my 0.02\$"
assert out == 'my 0.02$'