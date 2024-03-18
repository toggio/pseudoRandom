# pseudoRandom
## Universal Cross-Platform Multi-Language PRNG (Pseudo Random Number Generator)

The `pseudoRandom` library provides a consistent interface for generating pseudo-random numbers across multiple languages and platforms, including PHP, Python, and JavaScript. It's designed for a variety of applications, from simple simulations and games to more complex cryptographic operations.

### Features

- **Versatile**: Supports seed initialization and re-seeding for repeatable sequences.
- **Cross-platform Compatibility**: Works seamlessly in PHP, Python, and JavaScript environments.
- **Consistent Results Across Languages**: Given the same seed, it generates an identical sequence of pseudo-random numbers in all languages.
- **Flexible Seed Initialization**: Allows custom seed setting or defaults to the current epoch time in seconds if no seed is provided, ensuring unique sequences for each instance.
- **State Management**: Facilitates saving and restoring the generator's state for repeatable results.
- **Easy to Integrate**: Simplifies its inclusion into various projects, providing functionality to generate both random integers and byte strings without the need for complex setup.
- **Dieharder Test Suite Passed**: Demonstrates reliability and randomness (see dieharder.log for details)

### Installation

For PHP, include the library in your script:

```php
require_once('/path/to/pseudoRandom.php');
```

For Python, ensure the file is in your project directory or in the PYTHONPATH, and then import it:

```python
from pseudoRandom import pseudoRandom
```

For JavaScript, include the script in your HTML or import it in your project:

```html
<script src="path/to/pseudoRandom.js"></script>
```

### Usage

#### PHP

Initialize the generator:

```php
$random = new pseudoRandom();
$random->reSeed(12345); // Optional: re-seed the generator
```

Generate a random integer:

```php
echo $random->randInt(0, 100);
```

Generate a random byte string:

```php
echo $random->randBytes(10);
```

#### Python

Initialize the generator:

```python
random = pseudoRandom()
random.reSeed(12345) # Optional: re-seed the generator
```

Generate a random integer:

```python
print(random.randInt(0, 100))
```

Generate a random byte string:

```python
print(random.randBytes(10))
```

#### JavaScript

Initialize the generator:

```javascript
const random = new pseudoRandom();
random.reSeed(12345); // Optional: re-seed the generator
```

Generate a random integer:

```javascript
console.log(random.randInt(0, 100));
```

Generate a random byte string:

```javascript
console.log(random.randBytes(10));
```

## License
*pseudoRandom* is licensed under the Apache License, Version 2.0. You are free to use, modify, and distribute the library in compliance with the license.

Copyright (C) 2024 Luca Soltoggio - https://www.lucasoltoggio.it/
