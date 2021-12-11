import sys


def part1(lines):
    depth = 0
    horizontal = 0
    for line in lines:
        if 'forward' in line:
            n = int(line.split('forward ')[1])
            horizontal += n
        elif 'down' in line:
            n = int(line.split('down ')[1])
            depth += n
        elif 'up' in line:
            n = int(line.split('up ')[1])
            depth -= n
    
    print("horizontal: {}".format(horizontal))
    print("depth: {}".format(depth))
    
    print("A: {}".format(horizontal * depth))

def part2(lines):
    depth = 0
    horizontal = 0
    aim = 0
    for line in lines:
        if 'forward' in line:
            n = int(line.split('forward ')[1])
            horizontal += n
            depth += aim*n
        elif 'down' in line:
            n = int(line.split('down ')[1])
            aim += n
        elif 'up' in line:
            n = int(line.split('up ')[1])
            aim -= n
    
    print("horizontal: {}".format(horizontal))
    print("depth: {}".format(depth))
    print("aim: {}".format(depth))
    
    print("A: {}".format(horizontal * depth))

if __name__ == "__main__":
    filename = sys.argv[1]

    with open(filename, 'r') as f:
        lines = [line.rstrip('\n') for line in f]
    part2(lines)
