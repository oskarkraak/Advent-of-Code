from heapq import heappop, heappush, heapify

def part1():
    max = 0
    current = 0
    for line in file:
        if line == "\n":
            if current > max:
                max = current
            current = 0
        else:
            current += int(line)
    print("Part 1:", max)

def part2():
    heap = []
    heapify(heap)
    current = 0
    for line in file:
        if line == "\n":
            heappush(heap, -current)
            current = 0
        else:
            current += int(line)
    sum = 0
    for i in range(3):
        sum += heappop(heap)
    print("Part 2:", -sum)

file = open("input.txt").readlines()
file.append("\n")
part1()
part2()