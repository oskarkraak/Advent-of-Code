def part1():
    score = 0
    for line in file:
        opponents_shape = line[0]
        my_shape = line[2]
        if my_shape == "X":
            score += 1
            if opponents_shape == "A":
                score += 3
            if opponents_shape == "C":
                score += 6
        if my_shape == "Y":
            score += 2
            if opponents_shape == "B":
                score += 3
            if opponents_shape == "A":
                score += 6
        if my_shape == "Z":
            score += 3
            if opponents_shape == "C":
                score += 3
            if opponents_shape == "B":
                score += 6
    print("Part 1:", score)


def part2():
    shapes = ["A", "B", "C"]
    results = ["X", "Y", "Z"]
    score = 0
    for line in file:
        opponents_shape = line[0]
        result = line[2]
        shape_index = shapes.index(opponents_shape)
        result_index = results.index(result)
        my_shape = (shape_index + 3 + result_index - 1) % 3
        score += my_shape + 1
        score += result_index * 3
    print("Part 2:", score)


file = open("input.txt").readlines()
part1()
part2()
