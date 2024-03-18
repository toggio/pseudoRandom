class pseudoRandom {
	constructor(s = null) {
		this.a = 1664525;
		this.c = 1013904223;
		this.m = 4294967296; // 2^32
		this.counter = 0;
		this.reSeed(s);
	}

	reSeed(s = null) {
		if (typeof s === 'string') {
			// Se s è una stringa, usa la funzione crc32 per impostare il seed
			this.RSeed = this.crc32(s);
		} else if (s !== null) {
			this.RSeed = Math.abs(parseInt(s));
		} else {
			// Imposta un seed iniziale basato sul tempo attuale se non fornito
			this.RSeed = Math.ceil(Date.now() / 1000);
		}
		this.c = 1013904223; // Reset del seed con una chiamata iniziale per mescolare i risultati
		this.counter = 0;
	}

	crc32 (r) {
		var a, o = [], c;
		for (c = 0; c < 256; c++) {
			a = c;
			for (var f = 0; f < 8; f++) {
				a = a & 1 ? 3988292384 ^ (a >>> 1) : a >>> 1;
			}
			o[c] = a;
		}
		for (var n = -1, t = 0; t < r.length; t++) {
			n = n >>> 8 ^ o[255 & (n ^ r.charCodeAt(t))];
		}
		return (-1 ^ n) >>> 0;
	}

	saveStatus() {
		this.savedRSeed = this.RSeed;
	}

	restoreStatus() {
		if (this.savedRSeed !== null) {
			this.RSeed = this.savedRSeed;
		}
	}

	randInt(min = 0, max = 255) {
	// Genera un numero casuale nel range specificato
		// alert (this.RSeed);
		// alert (this.counter);
		this.c = this.crc32(String(this.counter)+String(this.RSeed)+String(this.counter));
		// alert (this.c);
		this.RSeed = (this.RSeed * this.a + this.c) % this.m;
		this.counter++;
		return Math.floor((this.RSeed / this.m) * (max - min + 1) + min);
	}
}
