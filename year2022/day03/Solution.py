def part1():
    sum_of_priorities = 0
    for line in file:
        item_types = set()
        for i in range(0, int(len(line) / 2)):
            item_types.add(line[i])
        for i in range(int(len(line) / 2), len(line)):
            if line[i] in item_types:
                item_types.remove(line[i])
                if ord(line[i]) >= ord('a'):
                    sum_of_priorities += ord(line[i]) - ord('a') + 1
                else:
                    sum_of_priorities += ord(line[i]) - ord('A') + 26 + 1
    print("Part 1:", sum_of_priorities)


def part2():
    file.append("\n")
    sum_of_priorities = 0
    group_item_types = set()
    for i in range(len(file)):
        if i != 0 and i % 3 == 0:
            # Evaluate group
            common_item_type = list(group_item_types)[0]
            if ord(common_item_type) >= ord('a'):
                sum_of_priorities += ord(common_item_type) - ord('a') + 1
            else:
                sum_of_priorities += ord(common_item_type) - ord('A') + 26 + 1
            # Start new group
            group_item_types.clear()
        elf_item_types = set()
        line = file[i].strip()
        for j in range(len(line)):
            elf_item_types.add(line[j])
        if i % 3 == 0:
            group_item_types = set.union(elf_item_types, group_item_types)
        else:
            group_item_types = set.intersection(elf_item_types, group_item_types)
    print("Part 2:", sum_of_priorities)


file = open("input.txt").readlines()
part1()
part2()
