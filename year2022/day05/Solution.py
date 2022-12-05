import copy


def part1(list_of_stacks):
    for i in range(starting_arrangement_end_index + 1, len(file)):
        line = file[i]
        # Decode instructions
        count = int(line.split(" ")[1])
        from_stack = int(line.split(" ")[3]) - 1
        to_stack = int(line.split(" ")[5]) - 1
        # Move crates
        for j in range(count):
            crate = list_of_stacks[from_stack].pop()
            list_of_stacks[to_stack].append(crate)
    top_of_stack = ""
    for i in range(num_stacks):
        if len(list_of_stacks[i]) != 0:
            top_of_stack += list_of_stacks[i][-1]
    print("Part 1:", top_of_stack)


def part2(list_of_stacks):
    for i in range(starting_arrangement_end_index + 1, len(file)):
        line = file[i]
        # Decode instructions
        count = int(line.split(" ")[1])
        from_stack = int(line.split(" ")[3]) - 1
        to_stack = int(line.split(" ")[5]) - 1
        # Move crates
        crates = list_of_stacks[from_stack][-count:]
        del list_of_stacks[from_stack][-count:]
        list_of_stacks[to_stack].extend(crates)
    top_of_stack = ""
    for i in range(num_stacks):
        if len(list_of_stacks[i]) != 0:
            top_of_stack += list_of_stacks[i][-1]
    print("Part 2:", top_of_stack)


file = open("input.txt").readlines()
# Parse input
starting_arrangement_end_index = 0
for line in file:
    if line.strip() == "":
        break
    else:
        starting_arrangement_end_index += 1
list_of_stacks = []
num_stacks = int(file[starting_arrangement_end_index - 1].split(" ")[-1])
for i in range(num_stacks):
    list_of_stacks.append([])
for i in range(starting_arrangement_end_index - 1):
    line = file[starting_arrangement_end_index - 2 - i]
    for j in range(num_stacks):
        if len(line) >= j * 4 + 1:
            crate = line[j * 4 + 1]
            if crate != " ":
                list_of_stacks[j].append(crate)
part1(copy.deepcopy(list_of_stacks))
part2(copy.deepcopy(list_of_stacks))
