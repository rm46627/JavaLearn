export interface User {
    id: bigint
    username: string
    password: string
    email: string
    enabled: boolean
    admin: boolean
}