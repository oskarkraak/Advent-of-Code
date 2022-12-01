from heapq import heappop, heappush, heapify


def part1():
    max_calories = 0
    current_calories = 0
    for line in file:
        if line == "\n":
            if current_calories > max_calories:
                max_calories = current_calories
            current_calories = 0
        else:
            current_calories += int(line)
    print("Part 1:", max_calories)


def part2():
    heap = []
    heapify(heap)
    current_calories = 0
    for line in file:
        if line == "\n":
            heappush(heap, -current_calories)
            current_calories = 0
        else:
            current_calories += int(line)
    sum_of_top3 = 0
    for i in range(3):
        sum_of_top3 += heappop(heap)
    print("Part 2:", -sum_of_top3)


file = open("input.txt").readlines()
file.append("\n")
part1()
part2()