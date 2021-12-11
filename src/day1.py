import sys

filename = sys.argv[1]
prev = 9999999999
count = 0

vals = []
with open(filename, 'r') as f:
    lines = f.readlines()
    for line in lines:
        cur = int(line)
        vals += [cur]
        #if cur > prev:
        #    count += 1
        #prev = cur

for i in range(len(vals)-2):
    cur = sum(vals[i:i+3])
    print(cur)
    if cur > prev:
        count += 1
    prev = cur

print(count)
    
