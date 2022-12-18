def part1(cubes):
    surface_area = 0
    for i in range(len(cubes)):
        new_cube = cubes[i]
        surface_area += 6
        for j in range(i):
            old_cube = cubes[j]
            if is_adjacent(new_cube, old_cube):
                surface_area -= 2
    print("Part 1:", surface_area)


def is_adjacent(cube1, cube2) -> bool:
    if cube1 == cube2:
        return False
    difference = 0
    for i in range(len(cube1)):
        difference += abs(cube1[i] - cube2[i])
    return difference <= 1


def get_cubes(file: list[str]):
    cubes = list()
    for line in file:
        nums = list()
        for num in line.strip().split(","):
            nums.append(int(num))
        cubes.append(nums)
    return cubes


file = open("input.txt").readlines()
part1(get_cubes(file))
