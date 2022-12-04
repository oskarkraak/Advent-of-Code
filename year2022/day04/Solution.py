def part1():
    fully_contained = 0
    for line in file:
        # Parse input
        assignment1 = line.split(",")[0]
        assignment2 = line.split(",")[1]
        start1 = int(assignment1.split("-")[0])
        end1 = int(assignment1.split("-")[1])
        start2 = int(assignment2.split("-")[0])
        end2 = int(assignment2.split("-")[1])
        # Check if one is fully contained
        if (start1 <= start2 and end1 >= end2) or (start1 >= start2 and end1 <= end2):
            fully_contained += 1
    print("Part 1:", fully_contained)


def part2():
    overlapping = 0
    for line in file:
        # Parse input
        assignment1 = line.split(",")[0]
        assignment2 = line.split(",")[1]
        start1 = int(assignment1.split("-")[0])
        end1 = int(assignment1.split("-")[1])
        start2 = int(assignment2.split("-")[0])
        end2 = int(assignment2.split("-")[1])
        # Check if they are overlapping
        if (start1 <= start2 <= end1) or (start2 <= start1 <= end2):
            overlapping += 1
    print("Part 2:", overlapping)


file = open("input.txt").readlines()
part1()
part2()
