import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

fun  main(){
    val p = 5
    val q = 11
    println("p = $p , q = $q")
    val bitLength = 4

    println("Running RSA...")
    println("Generating public/private keypair...")
    val (public, private) = generate_keypair(p, q, Math.pow(2.0, bitLength.toDouble()).toInt())
    println("Public Key: $public")
    println("Private Key: $private")

    val msg = "tran thien demo"
    println("Message: $msg")
    val encryptedMsg = encrypt(msg, public)
    println("Encrypted msg: $encryptedMsg")
    val desc = decrypt(encryptedMsg, private)
    println("Decrypted msg: $desc")

}

private fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

private fun modInverse(a: Int, m: Int): Int {
    for (x in 1 until m) {
        if (a * x % m == 1) {
            return x
        }
    }
    return -1
}

fun isPrime(n: Int): Boolean {
    if (n < 2) {
        return false
    } else if (n == 2) {
        return true
    } else {
        for (i in 2..sqrt(n.toDouble()).toInt() + 1 step 2) {
            if (n % i == 0) {
                return false
            }
        }
    }
    return true
}

private fun generate_keypair(p: Int, q: Int, keysize: Int): Pair<Pair<Int, Int>, Pair<Int, Int>> {
    // keysize is the bit length of n so it must be in range(nMin,nMax+1).
    // shl is a bitwise operator
    // x shl y is same as multiplying x by 2**y
    // i am doing this so that p and q values have similar bit-length.
    // this will generate an n value that's hard to factorize into p and q.

    val nMin = 1 shl (keysize - 1)
    val nMax = (1 shl keysize) - 1
    val primes = mutableListOf(2)
    // we choose two prime numbers in range(start, stop) so that the difference of bit lengths is at most 2.
    val start = 1 shl (keysize / 2 - 1)
    val stop = 1 shl (keysize / 2 + 1)

    if (start >= stop) {
        return Pair(Pair(0, 0), Pair(0, 0))
    }

    for (i in 3..stop step 2) {
        var isPrime = true
        for (p in primes) {
            if (i % p == 0) {
                isPrime = false
                break
            }
        }
        if (isPrime) {
            primes.add(i)
        }
    }

    while (primes.isNotEmpty() && primes[0] < start) {
        primes.removeAt(0)
    }

    // choosing p and q from the generated prime numbers.
    var p = 0
    var q = 0
    while (primes.isNotEmpty()) {
        p = primes.random()
        primes.remove(p)
        val q_values = primes.filter { q -> nMin <= p * q && p * q <= nMax }
        if (q_values.isNotEmpty()) {
            q = q_values.random()
            break
        }
    }
    println("$p $q")
    val n = p * q
    val phi = (p - 1) * (q - 1)

    // generate public key 1 < e < phi(n)
    var e = Random.nextInt(1, phi)
    var g = gcd(e, phi)


    while (g != 1) {
        // as long as gcd(1,phi(n)) is not 1, keep generating e
        e = Random.nextInt(1, phi)
        g = gcd(e, phi)
    }

    // generate private key
    val d = modInverse(e, phi)

    // public key (e,n)
    // private key (d,n)
    return Pair(Pair(e, n), Pair(d, n))
}

private fun encrypt(msgPlaintext: String, packages: Pair<Int, Int>): List<Int> {
    val (e, n) = packages
    val ciphertext: MutableList<Int> = mutableListOf()
    for (c in msgPlaintext) {
        val temp = c.hashCode().toBigInteger().pow(e) % n.toBigInteger()
        ciphertext.add(temp.toInt() )
    }
    return ciphertext
}

private fun decrypt(msg: List<Int>, packages: Pair<Int, Int>): String {
    val (d, n) = packages
    var msgPlaintext = ""
    for (c in msg){
        val char = c.toBigInteger().pow(d) % n.toBigInteger()
        msgPlaintext += char.toInt().toChar().toString()
    }
    return msgPlaintext
}