def part1():
    start_sequence_length = 4
    start_sequence_index = get_start_sequence_index(start_sequence_length)
    print("Part 1:", start_sequence_index+1)


def part2():
    start_sequence_length = 14
    start_sequence_index = get_start_sequence_index(start_sequence_length)
    print("Part 2:", start_sequence_index+1)


def get_start_sequence_index(start_sequence_length):
    start_sequence = ""
    for i in range(len(datastream_buffer)):
        if datastream_buffer[i] in start_sequence:
            repeated_char_index = start_sequence.index(datastream_buffer[i])
            start_sequence = start_sequence[repeated_char_index+1:]
        start_sequence += datastream_buffer[i]
        if len(start_sequence) == start_sequence_length:
            return i
    return -1


datastream_buffer = open("input.txt").readlines()[0]
part1()
part2()
