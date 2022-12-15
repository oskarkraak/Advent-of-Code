import re


def part1(y: int):
    sensor_list = get_sensor_list(file)
    beacon_list = get_beacon_list(file)
    no_beacon_ranges = get_ranges(sensor_list, beacon_list, y)
    no_beacon_coordinates = range_set(no_beacon_ranges)
    no_beacon_count = len(no_beacon_coordinates)
    print("Part 1:", no_beacon_count)


def get_sensor_list(file: list[str]) -> list[tuple[int, int]]:
    sensors = list()
    for line in file:
        ints = get_ints(line)
        sensor = (int(ints[0]), int(ints[1]))
        sensors.append(sensor)
    return sensors


def get_beacon_list(file: list[str]) -> list[tuple[int, int]]:
    beacons = list()
    for line in file:
        ints = get_ints(line)
        beacon = (int(ints[2]), int(ints[3]))
        beacons.append(beacon)
    return beacons


def get_ints(string: str) -> list[int]:
    values = re.findall(r"\d+|-\d+", string)
    ints = list()
    for value in values:
        ints.append(int(value))
    return values


def get_ranges(sensor_list: list[tuple[int, int]], beacon_list: list[tuple[int, int]], y: int) -> list[tuple[int, int]]:
    ranges = list()
    for i in range(len(sensor_list)):
        sensor = sensor_list[i]
        beacon = beacon_list[i]
        x = sensor[0]
        vertical_distance_to_y = abs(y - sensor[1])
        length = (manhattan_distance(sensor, beacon) - vertical_distance_to_y)
        left = x - length
        right = x + length
        ranges.append((left, right))
    return ranges


def manhattan_distance(a: tuple[int, int], b: tuple[int, int]) -> int:
    return abs(a[0] - b[0]) + abs(a[1] - b[1])


def range_set(ranges: list[tuple[int, int]]) -> set[int]:
    tuples = set()
    for number_range in ranges:
        number_range_set = range(number_range[0], number_range[1])
        tuples = tuples.union(number_range_set)
    return tuples


file = open("input.txt").readlines()
part1(2000000)
