export class Page {
    id: bigint = BigInt(0)
    courseId: bigint = BigInt(0)
    pageOrder: number = 0
    name!: string
    data!: string
    type!: string
}