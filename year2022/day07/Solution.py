class Dir:
    def __init__(self, name: str, parent, size: int = 0):
        self.name = name
        self.parent = parent
        self.size = size
        self.children = dict()
        self.is_dir = size == 0

    def add_child(self, node):
        if node not in self.children:
            self.children.update({node.name: node})

    def calculate_size(self) -> int:
        if self.size != 0:
            return self.size
        for child in self.children.values():
            self.size += child.calculate_size()
        return self.size


def part1(size_limit: int):
    dir_tree = get_dir_tree(file)
    dir_tree.calculate_size()
    dir_sizes = bfs_get_dir_sizes(dir_tree)
    sum_of_sizes = 0
    for dir_size in dir_sizes:
        if dir_size <= size_limit:
            sum_of_sizes += dir_size
    print("Part 1:", sum_of_sizes)


def part2(total_space_available: int, total_space_needed: int):
    dir_tree = get_dir_tree(file)
    dir_tree.calculate_size()
    dir_sizes = bfs_get_dir_sizes(dir_tree)
    space_used = dir_tree.size
    space_to_free = space_used + total_space_needed - total_space_available
    smallest_sufficing_dir_size = float("inf")
    for dir_size in dir_sizes:
        if space_to_free < dir_size < smallest_sufficing_dir_size:
            smallest_sufficing_dir_size = dir_size
    print("Part 2:", smallest_sufficing_dir_size)


def get_dir_tree(commands) -> Dir:
    root = Dir("/", None)
    current = root
    for i in range(1, len(commands)):
        command = commands[i].strip()
        if get_keyword(command) == "cd":
            dir_name = get_directory(command)
            if dir_name == "..":
                current = current.parent
            else:
                new_dir = Dir(dir_name, current)
                current.add_child(new_dir)
                current = new_dir
        elif get_keyword(command) == "ls":
            i += 1
            while get_keyword(commands[i]) != "cd":
                file = commands[i]
                size = 0
                if not is_dir(file):
                    size = get_size(file)
                new_dir = Dir(get_name(file), current, size)
                current.add_child(new_dir)
                i += 1
                if i >= len(commands):
                    break
    return root


def bfs_get_dir_sizes(start: Dir) -> list[int]:
    dir_sizes = []
    queue = [start]
    while len(queue) != 0:
        node = queue.pop(0)
        for child in node.children.values():
            queue.append(child)
        if node.is_dir:
            dir_sizes.append(node.size)
    return dir_sizes


def get_keyword(command: str) -> str:
    return command.split(" ")[1]


def get_directory(command: str) -> str:
    return command.split(" ")[2]


def is_dir(file: str) -> bool:
    return file.split(" ")[0] == "dir"


def get_size(file: str) -> int:
    return int(file.split(" ")[0])


def get_name(file: str) -> str:
    return file.split(" ")[1]


file = open("input.txt").readlines()
for i in range(len(file)):
    file[i] = file[i].strip()
part1(100000)
part2(70000000, 30000000)
